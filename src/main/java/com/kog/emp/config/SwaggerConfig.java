package com.kog.emp.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;



/**
 * clas having swagger configuration
 */
    @OpenAPIDefinition(
            info=@Info(
                    title = "EmployeeManagement API",
                    description="API for Employee Management Project"
                    ,termsOfService="t&c",
                    version="V3"
            ),
            servers = {
                    @Server(
                            description = "this url is for dev",
                            url = "http://localhost:8091"
                    ),
                    @Server(
                            description = "this url is for user",
                            url = "http://localhost:8091"
                    )
            }
    )

    @SecurityScheme(
            name = "EmployeeManagement",
            scheme = "bearer",
            type = SecuritySchemeType.HTTP,
            in = SecuritySchemeIn.HEADER
    )
/*
  This class is for provide basic Swagger configuration like Security Scheme , Server , and info
 */
    public class SwaggerConfig {

    }
