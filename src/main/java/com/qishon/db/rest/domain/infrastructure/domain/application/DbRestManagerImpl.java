package com.qishon.db.rest.domain.infrastructure.domain.application;

import com.qishon.db.rest.domain.application.DbRestManager;
import org.springframework.stereotype.Component;

/**
 * DB RESTful Manager
 *
 * @author Hero
 */

@Component
public class DbRestManagerImpl implements DbRestManager {
    public String usage() {
        return " Qishon DB RESTful API";
    }

    ;

    public String url() {
        return "http://127.0.0.1:8080";
    }

    ;
}
