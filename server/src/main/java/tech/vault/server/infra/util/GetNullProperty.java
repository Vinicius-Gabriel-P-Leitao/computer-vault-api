package tech.vault.server.infra.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

@UtilityClass
public class GetNullProperty {
    public static String[] nullProperty(Object source) {
        final BeanWrapper wrapperSource = new BeanWrapperImpl(source);

        return Stream.of(wrapperSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrapperSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}