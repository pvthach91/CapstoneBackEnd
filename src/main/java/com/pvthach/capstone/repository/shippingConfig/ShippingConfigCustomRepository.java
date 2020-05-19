package com.pvthach.capstone.repository.shippingConfig;

import com.pvthach.capstone.dto.ShippingConfigDTO;
import com.pvthach.capstone.dto.ShippingConfigSearchCriteria;
import com.pvthach.capstone.service.Page;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface ShippingConfigCustomRepository {

    Page<List<ShippingConfigDTO>> searchShippingConfigs(ShippingConfigSearchCriteria criteria);
}