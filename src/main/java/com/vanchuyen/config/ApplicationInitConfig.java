package com.vanchuyen.config;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.enums.Role;
import com.vanchuyen.repository.NguoiDungRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    // spotless:off

    
    // spotless:on
    @Bean
    ApplicationRunner applicationRunner(NguoiDungRepository userRepository) {
        return args -> {
            if (userRepository.findByTaiKhoan("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                NguoiDung user = NguoiDung.builder()
                        .taiKhoan("admin")
                        .matKhau(passwordEncoder.encode("admin"))
                        // .role(Role.ADMIN.name())
                        .build();
                userRepository.save(user);
                log.warn("Admin user created successfully with password admin");
            }
        };
    }
}
