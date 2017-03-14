package com.qishon.db.rest.domain.infrastructure.domain.service;

import com.qishon.db.rest.domain.event.DbRestEvent;
import com.qishon.db.rest.domain.factory.DbRestFactory;
import com.qishon.db.rest.domain.model.DbRest;
import com.qishon.db.rest.domain.service.DbRestService;
import com.qishon.db.rest.domain.specification.DbRestSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * DbRest service implementation
 *
 * @author Hero
 */
@Service
public class DbRestServiceImpl implements DbRestService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private DbRestSpec dbRestSpec;

    @Autowired
    private DbRestFactory dbRestFactory;
    private void create(DbRest dbRest) {
        DbRestEvent e = new DbRestEvent(DbRestEvent.CREATED_TYPE, dbRest);
        eventPublisher.publishEvent(e);
    }

    @Override
    public String run(String tableName, String dmlAction, String key) {
        String params = "\"params\":{\"tableName\":\"" + tableName + "\",\"dmlAction\":\"" + dmlAction + "\",\"key\":\"" + key + "\"}";
        String status = "\"status\":{\"code\":\"200\",\"message\":\"submitted\"}";
        if (!dbRestSpec.isTableNameLegal(tableName)) {
            status = "\"status\":{\"code\":\"20101\",\"message\":\"tableName(表名)" + tableName + "不在处理范围内\"}";
        } else if (!dbRestSpec.isDmlActionLegal(dmlAction)) {
            status = "\"status\":{\"code\":\"20102\",\"message\":\"dmlAction(动作)仅允许值为delete/insert/update，实际值为" + dmlAction + "\"}";
        } else if (!dbRestSpec.isKeyLegal(key)) {
            status = "\"status\":{\"code\":\"20103\",\"message\":\"key(主键)必须是整数，实际值为" + key + "\"}";
        } else {
            create(dbRestFactory.createFromWebApi(tableName, dmlAction, key));
        }
        return "{" + params + "," + status + "}";
    }
}
