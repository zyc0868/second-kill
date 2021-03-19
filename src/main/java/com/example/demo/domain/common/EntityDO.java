package com.example.demo.domain.common;

import java.io.Serializable;
import java.util.Date;

public interface EntityDO extends HasId<Long>, Serializable {

    String getTableName();

    Date getGmtCreate();

    void setGmtCreate(Date gmtCreate);

    Date getGmtModified();

    void setGmtModified(Date gmtModified);

}
