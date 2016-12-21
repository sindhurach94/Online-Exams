package se.example2.softhouse.Application;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import se.example2.softhouse.Application.Note.Note;
import se.example2.softhouse.core.Question;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by hxs on 2016-08-16.
 */
public class DemoConfiguration extends Configuration implements AssetsBundleConfiguration{


    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    private DataSourceFactory dataSourceFactory = new DataSourceFactory();
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
