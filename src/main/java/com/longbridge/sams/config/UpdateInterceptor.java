package com.longbridge.sams.config;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.longbridge.sams.SchoolContext;
import com.longbridge.sams.model.AbstractSchoolEntity;

public class UpdateInterceptor extends EmptyInterceptor {
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		Long tenantId = SchoolContext.getCurrentTenant();
		if (tenantId == null) { // admin
			return false;
		}
		if (entity instanceof AbstractSchoolEntity) {
			((AbstractSchoolEntity) entity).setSchoolId(SchoolContext.getCurrentTenant());
		}
		return true;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		Long tenantId = SchoolContext.getCurrentTenant();
		if (tenantId == null) { // admin
			return;
		}
		if (entity instanceof AbstractSchoolEntity) {
			((AbstractSchoolEntity) entity).setSchoolId(SchoolContext.getCurrentTenant());
		}
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		Long tenantId = SchoolContext.getCurrentTenant();
		if (tenantId == null) { // admin
			return false;
		}
		if (entity instanceof AbstractSchoolEntity) {
			((AbstractSchoolEntity) entity).setSchoolId(SchoolContext.getCurrentTenant());
		}
		return true;
	}

}
