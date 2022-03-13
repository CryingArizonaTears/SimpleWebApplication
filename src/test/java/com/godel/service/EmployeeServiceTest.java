package com.godel.service;

import com.godel.api.dao.IEmployeeDao;
import com.godel.dto.EmployeeDto;
import com.godel.model.Gender;
import com.godel.modelMapperMethods.ExtendedModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private IEmployeeDao employeeDao;
    @Spy
    private ExtendedModelMapper modelMapper;

    private final EmployeeDto employeeDto = new EmployeeDto();

    @BeforeEach
    void beforeTests() {
        employeeDto.setEmployeeId(1L);
        employeeDto.setFirstName("test");
        employeeDto.setLastName("test");
        employeeDto.setDepartmentId(1L);
        employeeDto.setJobTitle("test");
        employeeDto.setGender(Gender.MALE);
        employeeDto.setDateOfBirth(LocalDate.of(2000,11,11));
        employeeService.save(employeeDto);
    }

    @Test
    void testSave_Successful() {
        Mockito.verify(employeeDao).save(ArgumentMatchers.argThat(employeeForSave ->
                employeeForSave.getEmployeeId().equals(employeeDto.getEmployeeId()) &&
                employeeForSave.getFirstName().equals(employeeDto.getFirstName()) &&
                employeeForSave.getLastName().equals(employeeDto.getLastName()) &&
                employeeForSave.getDepartmentId().equals(employeeDto.getDepartmentId()) &&
                employeeForSave.getJobTitle().equals(employeeDto.getJobTitle()) &&
                employeeForSave.getGender().equals(employeeDto.getGender()) &&
                employeeForSave.getDateOfBirth().equals(employeeDto.getDateOfBirth())));
    }

    @Test
    void testUpdate_Successful() {
        employeeDto.setFirstName("testEdit");
        employeeDto.setLastName("testEdit");
        employeeService.update(employeeDto);
        Mockito.verify(employeeDao).update(ArgumentMatchers.argThat(employeeForSave ->
                employeeForSave.getEmployeeId().equals(employeeDto.getEmployeeId()) &&
                        employeeForSave.getFirstName().equals(employeeDto.getFirstName()) &&
                        employeeForSave.getLastName().equals(employeeDto.getLastName()) &&
                        employeeForSave.getDepartmentId().equals(employeeDto.getDepartmentId()) &&
                        employeeForSave.getJobTitle().equals(employeeDto.getJobTitle()) &&
                        employeeForSave.getGender().equals(employeeDto.getGender()) &&
                        employeeForSave.getDateOfBirth().equals(employeeDto.getDateOfBirth())));
    }

    @Test
    void testDelete_Successful() {
        employeeService.delete(1L);
        Mockito.verify(employeeDao).delete(1L);
    }
}