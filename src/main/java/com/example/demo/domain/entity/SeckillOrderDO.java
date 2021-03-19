package com.example.demo.domain.entity;

import com.example.demo.domain.common.EntityDO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "seckillOrder")
public class SeckillOrderDO implements EntityDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "goodsId", nullable = false)
    private Long goodsId;

    @Column(name = "orderId", nullable = false)
    private Long orderId;

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
        return "seckillOrder";
    }
}
