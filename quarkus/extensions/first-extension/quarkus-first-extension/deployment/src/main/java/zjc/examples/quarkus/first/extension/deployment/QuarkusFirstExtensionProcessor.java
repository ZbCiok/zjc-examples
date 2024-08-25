package zjc.examples.quarkus.first.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

import zjc.examples.quarkus.first.extension.runtime.ExampleService;

class QuarkusFirstExtensionProcessor {

    private static final String FEATURE = "quarkus-first-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem registerBeans() {
        return AdditionalBeanBuildItem.builder()
                .addBeanClass(ExampleService.class)
                .build();
    }
}
