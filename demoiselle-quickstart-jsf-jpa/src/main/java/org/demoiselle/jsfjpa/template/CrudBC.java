package org.demoiselle.jsfjpa.template;

import br.gov.frameworkdemoiselle.template.DelegateCrud;
import java.util.List;

public abstract class CrudBC<Domain, Key, DAO extends CrudDAO<Domain, Key>> extends DelegateCrud<Domain, Key, DAO> {

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
    public void delete(Key id) {
        onBeforeDelete(id);
        super.delete(id);
        onAfterDelete(id);
    }

    @Override
    public void delete(List<Key> ids) {
        onBeforeDelete(ids);
        super.delete(ids);
        onAfterDelete(ids);
    }

    @Override
    public Domain insert(Domain bean) {
        onBeforeInsert(bean);
        onBeforeSave(bean);
        bean = super.insert(bean);
        onAfterInsert(bean);
        onAfterSave(bean);
        return bean;
    }

    @Override
    public Domain update(Domain bean) {
        onBeforeUpdate(bean);
        onBeforeSave(bean);
        bean = super.update(bean);
        onAfterUpdate(bean);
        onAfterSave(bean);
        return bean;
    }

}
