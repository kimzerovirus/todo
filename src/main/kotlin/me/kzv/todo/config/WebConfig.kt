package me.kzv.todo.config

import me.kzv.todo.interceptor.JwtInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver


@Configuration
class WebConfig(
    private val jwtInterceptor: JwtInterceptor
) : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/*/**")
            .addResourceLocations("classpath:/static/")
            .resourceChain(true)
            .addResolver(object : PathResourceResolver() {
                override fun getResource(resourcePath: String, location: Resource): Resource {
                    val requestedResource: Resource = location.createRelative(resourcePath)
                    return if (requestedResource.exists() && requestedResource.isReadable) requestedResource else ClassPathResource("/static/index.html")
                }
            })
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(jwtInterceptor)
            .order(1)
            .addPathPatterns("/api/v1/todo/**")
    }
}