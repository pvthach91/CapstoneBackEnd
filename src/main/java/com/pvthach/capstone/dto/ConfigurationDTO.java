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
}