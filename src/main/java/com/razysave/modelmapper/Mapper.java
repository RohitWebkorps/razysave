package com.razysave.modelmapper;

import com.razysave.dto.unit.UnitListDto;
import com.razysave.entity.property.Unit;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class Mapper {

    private static ModelMapper modelMapper = new ModelMapper();
      //  public static unitListDto mapUnitListDtoToUnit(Unit unit) {
       // return modelMapper.map(unit,UnitListDto.class);
 //   }
    public static List<Unit> mapUnitListDtoToUnitList(List<UnitListDto> unitListDtoList) {
        java.lang.reflect.Type targetListType = new TypeToken<List<Unit>>() {}.getType();
        return modelMapper.map(unitListDtoList, targetListType);
    }
}
