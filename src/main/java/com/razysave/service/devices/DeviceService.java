package com.razysave.service.devices;

import com.razysave.dto.device.ActiveDeviceDto;
import com.razysave.dto.device.DeviceListDto;
import com.razysave.entity.devices.Device;
import java.util.List;

public interface DeviceService {
    public List<DeviceListDto> getDevices(String deviceName);

    public List<ActiveDeviceDto> getDevicesOnAlert();

    public Device addDevice(Device device);

    public DeviceListDto getDeviceById(Integer id);

    public Device updateDevice(Integer deviceId, Device updatedDevice);

    public void deleteDeviceById(Integer deviceId);
}
