package org.demoiselle.jsfjpa.template;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.lifecycle.ViewScoped;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.template.PageBean;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.Parameter;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

public abstract class CrudMB<Domain, Key, BC extends CrudBC> implements PageBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private PaginationContext paginationContext;

    @ViewScoped
    @Inject
    private Parameter<String> id;

    @Inject
    @Name("demoiselle-jsf-bundle")
    private ResourceBundle bundle;

    @Inject
    private MessageContext messageContext;

    private String nextView;

    private String previousView;

    private Class<BC> businessClass;

    private transient BC business;

    private List<Domain> resultList;

    private transient DataModel<Domain> dataModel;

    private Map<Key, Boolean> selection = new HashMap<Key, Boolean>();

    private Domain bean;

    private Class<Domain> beanClass;

    private Class<Key> idClass;

    @Override
    public String getCurrentView() {
        FacesContext fc = Beans.getReference(FacesContext.class);
        return fc.getViewRoot().getViewId();
    }

    @Override
    public String getNextView() {
        if (nextView == null) {
            NextView annotation = this.getClass().getAnnotation(NextView.class);
            if (annotation != null) {
                nextView = annotation.value();
            }
        }
        return nextView;
    }

    @Override
    public String getPreviousView() {
        if (previousView == null) {
            PreviousView annotation = this.getClass().getAnnotation(PreviousView.class);
            if (annotation != null) {
                previousView = annotation.value();
            }
        }
        return previousView;
    }

    @Override
    public String getTitle() {
        return null;
    }

    private Class<BC> getBusinessClass() {
        if (this.businessClass == null) {
            this.businessClass = Reflections.getGenericTypeArgument(this.getClass(), 2);
        }
        return this.businessClass;
    }

    protected BC getBusiness() {
        if (this.business == null) {
            this.business = Beans.getReference(getBusinessClass());
        }
        return this.business;
    }

    public void clear() {
        this.dataModel = null;
        this.resultList = null;
        this.id = null;
        this.bean = null;
    }

    private Class<Domain> getBeanClass() {
        if (this.beanClass == null) {
            this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
        }
        return this.beanClass;
    }

    public DataModel<Domain> getDataModel() {
        if (this.dataModel == null) {
            this.dataModel = new ListDataModel<Domain>(this.getResultList());
        }
        return this.dataModel;
    }

    public List<Domain> getResultList() {
        if (this.resultList == null) {
            this.resultList = handleResultList();
        }
        return this.resultList;
    }

    protected List<Domain> handleResultList() {
        return getBusiness().findAll();
    }

    public String list() {
        clear();
        return getCurrentView();
    }

    public Map<Key, Boolean> getSelection() {
        return selection;
    }

    public void setSelection(Map<Key, Boolean> selection) {
        this.selection = selection;
    }

    public void clearSelection() {
        this.selection = new HashMap<Key, Boolean>();
    }

    public List<Key> getSelectedList() {
        List<Key> selectedList = new ArrayList<Key>();
        Iterator<Key> iter = getSelection().keySet().iterator();
        while (iter.hasNext()) {
            Key vId = iter.next();
            if (getSelection().get(vId)) {
                selectedList.add(vId);
            }
        }
        return selectedList;
    }

    public Pagination getPagination() {
        return paginationContext.getPagination(getBeanClass(), true);
    }

    public String deleteSelection() {
        try {
            boolean delete;
            for (Iterator<Key> iter = getSelection().keySet().iterator(); iter.hasNext();) {
                Key vId = iter.next();
                delete = getSelection().get(vId);

                if (delete) {
                    getBusiness().delete(vId);
                    iter.remove();
                }
            }
            addMessageContext("Registros deletados com sucesso!", SeverityType.INFO);
        } catch (Exception e) {
            addMessageContext(e.getMessage(), SeverityType.ERROR);
        }
        return getPreviousView();
    }

    protected Domain createBean() {
        return Beans.getReference(getBeanClass());
    }

    public Domain getBean() {
        if (this.bean == null) {
            initBean();
        }
        return this.bean;
    }

    private Class<Key> getIdClass() {
        if (this.idClass == null) {
            this.idClass = Reflections.getGenericTypeArgument(this.getClass(), 1);
        }
        return this.idClass;
    }

    @SuppressWarnings("unchecked")
    public Key getId() {
        Converter converter = getIdConverter();
        FacesContext fc = Beans.getReference(FacesContext.class);
        if (converter == null && String.class.equals(getIdClass())) {
            return (Key) id.getValue();

        } else if (converter == null) {
            throw new DemoiselleException(bundle.getString("id-converter-not-found", getIdClass().getCanonicalName()));

        } else {
            return (Key) converter.getAsObject(fc, fc.getViewRoot(), id.getValue());
        }
    }

    private Converter getIdConverter() {
        return Faces.getConverter(getIdClass());
    }

    private void initBean() {
        if (isUpdateMode()) {
            this.bean = this.loadBean();
        } else {
            setBean(createBean());
        }
    }

    public boolean isUpdateMode() {
        return getId() != null;
    }

    private Domain loadBean() {
        return handleLoad(getId());
    }

    protected void setBean(final Domain bean) {
        this.bean = bean;
    }

    private void addMessageContext(String msg, SeverityType st) {
        messageContext.add(msg, st);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    protected void addInformationMessage(String msg) {
        addMessageContext(msg, SeverityType.INFO);
    }

    protected void addWarningMessage(String msg) {
        addMessageContext(msg, SeverityType.WARN);
    }

    protected void addErrorMessage(String msg) {
        addMessageContext(msg, SeverityType.ERROR);
    }

    protected void addFatalMessage(String msg) {
        addMessageContext(msg, SeverityType.FATAL);
    }

    protected void onBeforeDelete() {
    }

    protected void onAfterDelete() {
    }

    protected void onBeforeInsert() {
    }

    protected void onAfterInsert() {
    }

    protected void onBeforeUpdate() {
    }

    protected void onAfterUpdate() {
    }

    protected void onBeforeSave() {
    }

    protected void onAfterSave() {
    }

    public String delete() {
        try {
            onBeforeDelete();
            getBusiness().delete(getId());
            onAfterDelete();
            addInformationMessage("Registro deletado com sucesso!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
            return "";
        }
        return getPreviousView();
    }

    public String insert() {
        try {
            onBeforeInsert();
            onBeforeSave();
            getBusiness().insert(getBean());
            onAfterInsert();
            onAfterSave();
            addInformationMessage("Registro inserido com sucesso!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
            return "";
        }
        return getPreviousView();
    }

    public String update() {
        try {
            onBeforeUpdate();
            onBeforeSave();
            getBusiness().update(getBean());
            onAfterUpdate();
            onAfterSave();
            addInformationMessage("Registro atualizado com sucesso!");
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
            return "";
        }
        return "";
    }

    protected Domain handleLoad(Key id) {
        return (Domain) getBusiness().load(id);
    }

}
