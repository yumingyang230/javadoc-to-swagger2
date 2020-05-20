package cn.nyhlw.doc2swagger.core.config;

public abstract class AbstractRestDocParseConfigAware implements IRestDocParseConfigAware{
    protected RestDocParseConfig _config;

    @Override
    public void setRestDocParseConfig(RestDocParseConfig config) {
        _config = config;
    }
}
