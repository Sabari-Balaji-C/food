package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Menu menu = new Menu();
        assertNotNull(menu);

        // Parameterized constructor
        Menu menuNew = new Menu(1, "CHICKEN BIRIYANI", 856.00, "750", "NON-VEG", 1);
        assertNotNull(menuNew);
        assertEquals(1, menuNew.getMenId());
        assertEquals("CHICKEN BIRIYANI", menuNew.getMenItem());
        assertEquals(856.00, menuNew.getMenPrice());
        assertEquals("750", menuNew.getMenCalories());
        assertEquals("NON-VEG", menuNew.getMenSpeciality());
        assertEquals(1, menuNew.getRestId());
    }

    @Test
    public void testGetterSetter() {
        Menu menu = new Menu();
        menu.setMenId(1);
        menu.setMenItem("CHICKEN BIRIYANI");
        menu.setMenPrice(856.00);
        menu.setMenCalories("750");
        menu.setMenSpeciality("NON-VEG");
        menu.setRestId(1);

        assertEquals(1, menu.getMenId());
        assertEquals("CHICKEN BIRIYANI", menu.getMenItem());
        assertEquals(856.00, menu.getMenPrice());
        assertEquals("750", menu.getMenCalories());
        assertEquals("NON-VEG", menu.getMenSpeciality());
        assertEquals(1, menu.getRestId());
    }

    
}
