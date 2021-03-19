package com.example.demo.domain.entity;

import com.example.demo.domain.common.EntityDO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: flyboy
 * @Date: 12/03/2021 19:30
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */


@Getter
@Setter
@Entity
@Table(name = "orderInfo")
// 这里如果表名设置为order 则会报错
public class OrderInfoDO implements EntityDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "goodsId", nullable = false, updatable = false)
    private Long goodsId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "goodsName", nullable = false)
    private String goodsName;

    @Column(name = "goods_count", nullable = false)
    private Integer goodsCount;

    @Column(name = "goodsPrice", nullable = false)
    private BigDecimal goodsPrice;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "payDate")
    private Date payDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmtCreate", nullable = false, updatable = false)
    private Date gmtCreate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmtModified", nullable = false)
    private Date gmtModified;

    @Override
    public String getTableName() {
        return "order";
    }

}
