package com.oranfish.sushiservice.service;

import com.oranfish.sushidao.dto.UCookieDTO;
import com.oranfish.sushidao.dto.UUserDTO;

import java.util.List;

public interface UUserService {
    List<UUserDTO> list();
    UCookieDTO get(String userToken);
}
