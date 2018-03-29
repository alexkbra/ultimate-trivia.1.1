package co.com.elpoli.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Empresas.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Empresas.class.getName() + ".publicidads", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Galerias.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Galerias.class.getName() + ".publicidads", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Publicidad.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Publicidad.class.getName() + ".galerias", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Publicidad.class.getName() + ".nivels", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Expedicion.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Expedicion.class.getName() + ".expedicionusers", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Expedicion.class.getName() + ".cuestionarios", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Expedicionuser.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Cuestionario.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Cuestionario.class.getName() + ".nivels", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Cuestionario.class.getName() + ".expedicions", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Nivel.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Nivel.class.getName() + ".publicidads", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Nivel.class.getName() + ".cuestionarios", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Pregunta.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Pregunta.class.getName() + ".respuestas", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Pregunta.class.getName() + ".cuestionarios", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Respuesta.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Nivel.class.getName() + ".preguntas", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Pregunta.class.getName() + ".nivels", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Pregunta.class.getName() + ".detalleexpedicionusers", jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Detalleexpedicionuser.class.getName(), jcacheConfiguration);
            cm.createCache(co.com.elpoli.domain.Expedicionuser.class.getName() + ".detalleexpedicionusers", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
