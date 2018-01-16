package com.scl.demo;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/10 15:14
 */
public class AppConfig {
    @Bean
    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {

        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
            @Override
            public void customize(Undertow.Builder builder) {
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
            }
        });
        // 这里也可以做其他配置
//        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));

        return factory;
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                    ((TomcatEmbeddedServletContainerFactory) container)
//                            .addConnectorCustomizers(new TomcatConnectorCustomizer() {
//                                @Override
//                                public void customize(Connector connector) {
//                                    connector.addUpgradeProtocol(new Http2Protocol());
//                                }
//
//                            });
//                }
//            }
//
//        };
//    }



}
