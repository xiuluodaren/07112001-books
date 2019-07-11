package com.books.domain;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-08 20:07<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class Car {

    private Long id;

    /**
     * 车辆名称
     */
    private String carName;

    /**
     * 车辆类型id
     */
    private Long carTypeId;

    /**
     * 车辆类型名称
     */
    private String carTypeName;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
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
}
