package com.adobe.cq.wcm.core.components.internal.models.v3;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.adobe.cq.wcm.core.components.models.ThemeSelector;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class ThemeSelectorImplTest

{
	private final AemContext ctx = new AemContext();
	private static final String DEFAULT_THEME_CF_PATH = "/content/dam/hello/themes/default-theme";
	private static final String PARENT_THEME_CF_PATH = "/content/dam/hello/themes/dark-theme";
	private static final String CHILD_THEME_CF_PATH = "/content/dam/hello/themes/default-theme";
	private static final String EMPTY_CF_PATH = "";

    @BeforeEach
    protected void setUp() {
    	ctx.addModelsForClasses(ThemeSelectorImpl.class);
    	ctx.load().json("/com/adobe/cq/wcm/core/components/internal/models/v3/themeselector/ThemeSelectorImplTest.json", "/content");
    }
    
    @Test
    void testResolveThemePath(){
    
    	ctx.currentResource("/content/themeselector");
    	ThemeSelector themeSelector = ctx.request().adaptTo(ThemeSelector.class);
    	String actual = themeSelector.resolveThemePath();
    	assertEquals(DEFAULT_THEME_CF_PATH,actual);
    }
    
    
    @Test
    void testResolveThemePathisEmpty(){
     	ctx.currentResource("/content/isempty");
    	ThemeSelector themeSelector = ctx.request().adaptTo(ThemeSelector.class);	
    	String actual = themeSelector.resolveThemePath();
    	assertEquals(EMPTY_CF_PATH,actual);
    }

    @Test
    void testResolveParentPageThemePath(){
    	
    	ctx.currentResource("/content/inheritance");
    	ThemeSelector themeSelector = ctx.request().adaptTo(ThemeSelector.class);	
    	String parentthemepath = themeSelector.resolveThemePath();
    	assertEquals(PARENT_THEME_CF_PATH,parentthemepath);
    }
    
    @Test
    void testResolveChildPageThemePath(){
    	ctx.currentResource("/content/inheritance/magazine");
    	ThemeSelector themeSelector = ctx.request().adaptTo(ThemeSelector.class);	
    	String parentthemepath = themeSelector.resolveThemePath();
    	assertEquals(CHILD_THEME_CF_PATH,parentthemepath);
    }
    
    @Test
    void testResolveInheritThemePath(){
    	
    	ctx.currentResource("/content/inheritance/adventures");
    	ThemeSelector themeSelector = ctx.request().adaptTo(ThemeSelector.class);	
    	String parentthemepath = themeSelector.resolveThemePath();
    	assertEquals(PARENT_THEME_CF_PATH,parentthemepath);
    }
    
    }


