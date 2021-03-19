package com.example.demo.domain.entity;

import com.example.demo.domain.common.EntityDO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "seckillGoods")
public class SeckillGoodsDO implements EntityDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "goodsName" , nullable = false)
    private String goodsName;

    @Column(name = "goodsTitle", nullable = false)
    private String goodsTitle;

    @Column(name = "goodsImg")
    private String goodsImg;

    @Column(name = "goodsPrice")
    private BigDecimal goodsPrice;

    @Column(name = "seckillPrice")
    private BigDecimal seckillPrice;

    @Column(name = "goodsStock")
    private Integer goodsStock;

    @Column(name = "goodsDetail")
    private String goodsDetail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

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
        return "seckillGoods";
    }
}
