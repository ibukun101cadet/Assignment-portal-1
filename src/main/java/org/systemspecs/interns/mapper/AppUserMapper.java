package org.systemspecs.interns.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.systemspecs.interns.domain.AppUser;


import org.systemspecs.interns.dto.CreateUserDTO;


@Mapper(componentModel = "spring", uses ={})
public interface AppUserMapper {


    @Mapping(source = "fullname", target = "fullname")
    AppUser toUser(CreateUserDTO createUserDTO);



}
