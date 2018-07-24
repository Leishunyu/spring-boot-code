package com.huaifeng.code.config;

import com.huaifeng.code.converter.TypeConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

/**
 * CodeAutoConfig
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Configuration
@ConditionalOnClass( {TypeConverter.class} )
@EnableConfigurationProperties( CodePreperties.class )
public class CodeAutoConfig {
    private final CodePreperties codePreperties;
    
    public CodeAutoConfig(CodePreperties codePreperties) {
        this.codePreperties = codePreperties;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public TypeConverter getTypeConverter(){
        if (!CollectionUtils.isEmpty(codePreperties.getTypesMap())){
            return new TypeConverter(codePreperties.getTypesMap());
        }
        return null;
    }
}
