package com.pvthach.capstone.dto;


import com.pvthach.capstone.model.State;

import java.io.Serializable;
import java.util.List;

/**
 * Created by THACH-PC
 */

public class ConfigurationDTO implements Serializable {

    private List<State> states;

    private List<ShippingConfigDTO> shippingConfigs;

    private List<AddressDTO> deliveryAddresses;

    private UserDTO user;


    public ConfigurationDTO() {
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<ShippingConfigDTO> getShippingConfigs() {
        return shippingConfigs;
    }

    public void setShippingConfigs(List<ShippingConfigDTO> shippingConfigs) {
        this.shippingConfigs = shippingConfigs;
    }

    public List<AddressDTO> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(List<AddressDTO> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}