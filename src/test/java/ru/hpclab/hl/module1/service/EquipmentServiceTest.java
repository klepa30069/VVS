/*
package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.EquipmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EquipmentServiceTest.EquipmentServiceTestConfiguration.class})
public class EquipmentServiceTest {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    public void testCreateAndGet(){
        //create
        Equipment equipment = new Equipment(UUID.randomUUID(), "bysical", false);

        Equipment savedEquipment = equipmentService.saveEquipment(equipment);

        Assertions.assertEquals(equipment.getType(), savedEquipment.getType());
        Assertions.assertEquals(equipment.getStatus(), savedEquipment.getStatus());
        Mockito.verify(equipmentRepository, Mockito.times(1)).save(equipment);

        //getAll
        List<Equipment> equipmentList = equipmentService.getAllEquipments();

        Assertions.assertEquals("runner", equipmentList.get(0).getType());
        Assertions.assertEquals("bysical", equipmentList.get(1).getType());
        Mockito.verify(equipmentRepository, Mockito.times(1)).findAll();
    }

    @Configuration
    static class EquipmentServiceTestConfiguration {
        @Bean
        EquipmentRepository equipmentRepository() {

            EquipmentRepository equipmentRepository = mock(EquipmentRepository.class);
            when(equipmentRepository.save(any())).thenReturn(new Equipment(UUID.randomUUID(), "bysical", false));
            when(equipmentRepository.findAll())
                    .thenReturn(Arrays.asList(new Equipment(UUID.randomUUID(), "runner", false),
                            new Equipment(UUID.randomUUID(), "bysical", false)));
            return equipmentRepository;

        }

        @Bean
        EquipmentService EquipmentService(EquipmentRepository equipmentRepository){
            return new EquipmentService(equipmentRepository);
        }
    }

}
*/
