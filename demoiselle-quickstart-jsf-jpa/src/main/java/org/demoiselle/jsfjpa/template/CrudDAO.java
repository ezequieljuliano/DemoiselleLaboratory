package org.demoiselle.jsfjpa.template;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;

public abstract class CrudDAO<Domain, Key> extends JPACrud<Domain, Key> {

    private static final long serialVersionUID = 1L;

    protected void onBeforeDelete(Key id) {
    }

    protected void onAfterDelete(Key id) {
    }

    protected void onBeforeDelete(List<Key> ids) {
    }

    protected void onAfterDelete(List<Key> ids) {
    }

    protected void onBeforeInsert(Domain bean) {
    }

    protected void onAfterInsert(Domain bean) {
    }

    protected void onBeforeUpdate(Domain bean) {
    }

    protected void onAfterUpdate(Domain bean) {
    }

    protected void onBeforeSave(Domain bean) {
    }

    protected void onAfterSave(Domain bean) {
    }

    @Override
    @Transactional
    public void delete(Key id) {
        onBeforeDelete(id);
        super.delete(id);
        onAfterDelete(id);
    }

    @Override
    @Transactional
    public Domain insert(Domain bean) {
        onBeforeInsert(bean);
        onBeforeSave(bean);
        bean = super.insert(bean);
        onAfterInsert(bean);
        onAfterSave(bean);
        return bean;
    }

    @Override
    @Transactional
    public Domain update(Domain bean) {
        onBeforeUpdate(bean);
        onBeforeSave(bean);
        bean = super.update(bean);
        onAfterUpdate(bean);
        onAfterSave(bean);
        return bean;
    }

}
