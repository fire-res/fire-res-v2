package io.github.fireres.gui.configurer.report;

import com.rits.cloning.Cloner;
import io.github.fireres.gui.controller.unheated.surface.groups.second.SecondGroup;
import io.github.fireres.gui.preset.Preset;
import io.github.fireres.unheated.surface.properties.UnheatedSurfaceProperties;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnheatedSurfaceSecondGroupConfigurer extends AbstractReportParametersConfigurer<SecondGroup> {

    private static final Integer THERMOCOUPLES_NUMBER_MIN = 1;
    private static final Integer THERMOCOUPLES_NUMBER_MAX = 100;

    private static final Integer BOUND_MIN = 2;
    private static final Integer BOUND_MAX = 10000;
    private static final Integer BOUND_INCREMENT = 100;

    private final Cloner cloner;

    @Override
    public void config(SecondGroup secondGroup, Preset preset) {
        val sampleProperties = secondGroup.getSample().getSampleProperties();
        val presetProperties = cloner.deepClone(preset.getProperties(UnheatedSurfaceProperties.class).getSecondGroup());

        sampleProperties.getReportPropertiesByClass(UnheatedSurfaceProperties.class)
                .orElseThrow()
                .setSecondGroup(presetProperties);

        resetThermocouplesCount(
                secondGroup.getSecondGroupParams().getThermocouples(),
                presetProperties.getThermocoupleCount());

        resetBound(
                secondGroup.getSecondGroupParams().getBound(),
                presetProperties.getBound());

        resetFunctionParameters(secondGroup.getFunctionParams(), presetProperties.getFunctionForm());
    }

    private void resetThermocouplesCount(Spinner<Integer> thermocouplesCount, Integer thermocouplesCountValue) {
        thermocouplesCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                THERMOCOUPLES_NUMBER_MIN,
                THERMOCOUPLES_NUMBER_MAX,
                thermocouplesCountValue));
    }

    private void resetBound(Spinner<Integer> bound, Integer boundValue) {
        bound.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                BOUND_MIN,
                BOUND_MAX,
                boundValue,
                BOUND_INCREMENT));
    }

}
