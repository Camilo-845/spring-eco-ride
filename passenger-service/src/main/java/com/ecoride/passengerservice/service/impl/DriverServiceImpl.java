package com.ecoride.passengerservice.service.impl;

import com.ecoride.passengerservice.dto.driver.DriverRequestDto;
import com.ecoride.passengerservice.dto.driver.DriverResponseDto;
import com.ecoride.passengerservice.exception.ResourceNotFoundException;
import com.ecoride.passengerservice.mapper.DriverMapper;
import com.ecoride.passengerservice.model.Driver;
import com.ecoride.passengerservice.repository.DriverRepository;
import com.ecoride.passengerservice.service.DriverService;
import com.ecoride.passengerservice.service.PassengerService;
import com.ecoride.passengerservice.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final DriverRepository driverRepository;
  private final DriverMapper driverMapper;
  private final PassengerService passengerService;

  @Override
  public Mono<DriverResponseDto> create(DriverRequestDto driverRequestDto) {

    return passengerService.getById(driverRequestDto.getPassengerId())
        .switchIfEmpty(Mono
            .error(new ResourceNotFoundException("Passenger not found with id: " + driverRequestDto.getPassengerId())))
        .flatMap(passenger -> {
          Driver driver = driverMapper.toEntity(driverRequestDto);
          driver.setPassengerId(passenger.getId());
          driver.setVerificationStatus(true);
          return driverRepository.save(driver);
        }).map(driverMapper::toDto);
  }

  @Override
  public Mono<DriverResponseDto> getById(String id) {
    UUID uuid = UUIDUtils.toUUID(id);
    return driverRepository.findById(uuid)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("DriverProfile not found with id: " + uuid)))
        .map(driverMapper::toDto);
  }

  @Override
  public Flux<DriverResponseDto> getAll() {
    return driverRepository.findAll().map(driverMapper::toDto);
  }

  @Override
  public Mono<DriverResponseDto> update(String id, DriverRequestDto driverProfileRequestDto) {
    UUID uuid = UUIDUtils.toUUID(id);
    return driverRepository.findById(uuid)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("DriverProfile not found with id: " + uuid)))
        .flatMap(driverProfile -> {
          driverProfile.setLicenseNo(driverProfileRequestDto.getLicenseNo());
          driverProfile.setCarPlate(driverProfileRequestDto.getCarPlate());
          driverProfile.setSeatsOffered(driverProfileRequestDto.getSeatsOffered());
          return driverRepository.save(driverProfile);
        }).map(driverMapper::toDto);
  }

  @Override
  public Mono<Void> delete(String id) {
    UUID uuid = UUIDUtils.toUUID(id);
    return driverRepository.findById(uuid)
        .switchIfEmpty(Mono.error(new ResourceNotFoundException("DriverProfile not found with id: " + uuid)))
        .flatMap(driver -> driverRepository.deleteById(uuid));

  }
}
