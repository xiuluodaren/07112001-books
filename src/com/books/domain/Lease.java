package com.books.domain;

import com.books.util.DateUtil;

import java.time.LocalDateTime;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-08 20:07<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class Lease {

    private Long id;

    /**
     * 车辆id
     */
    private Long carId;

    /**
     * 车辆名称
     */
    private String carName;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 车辆状态 0未出租 1已出租
     */
    private int state;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 租金
     */
    private Double amount;

    /**
     * 支付状态 0未支付 1已支付
     */
    private int isPayment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStartTime() {
        return DateUtil.getDateTimeAsString(this.startTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return DateUtil.getDateTimeAsString(this.endTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(int isPayment) {
        this.isPayment = isPayment;
    }
}
