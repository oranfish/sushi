package com.oranfish.sushiservice.service.impl;

import com.oranfish.sushidao.dto.UUserDTO;
import com.oranfish.sushidao.mapper.UUserMapper;
import com.oranfish.sushiservice.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UUserServiceImpl implements UUserService {

    @Autowired
    private UUserMapper uUserMapper;

    @Override
    public List<UUserDTO> list() {
        return uUserMapper.list();
    }
}
