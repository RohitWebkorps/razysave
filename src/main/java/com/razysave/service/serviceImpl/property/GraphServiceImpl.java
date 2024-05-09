package com.razysave.service.serviceImpl.property;

import com.razysave.dto.property.GraphMonthDto;
import com.razysave.dto.property.GraphWeekDto;
import com.razysave.dto.property.GraphYearDto;
import com.razysave.entity.property.GraphProperty;
import com.razysave.entity.property.GraphUnit;
import com.razysave.repository.property.GraphPropertyRepository;
import com.razysave.repository.property.GraphUnitRepository;
import com.razysave.service.property.GraphService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {
    @Autowired
    GraphPropertyRepository graphRepository;
    @Autowired
    GraphUnitRepository graphUnitRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<GraphWeekDto> getGraphPropertyByWeek(Integer propertyId) {
        GraphProperty graph = graphRepository.findByPropertyId(propertyId);
        return mapToGraphPropertyWeekDto(graph);
    }

    public List<GraphWeekDto> getGraphUnitByWeek(Integer propertyId) {
        GraphUnit graph = graphUnitRepository.findByPropertyId(propertyId);
        return mapToGraphUnitWeekDto(graph);
    }


    public List<GraphMonthDto> getGraphPropertyhByMonth(Integer propertyId) {
        GraphProperty graph = graphRepository.findByPropertyId(propertyId);
        return mapToGraphPropertyMonthDto(graph);
    }

    public List<GraphMonthDto> getGraphUnitByMonth(Integer propertyId) {
        GraphUnit graph = graphUnitRepository.findByPropertyId(propertyId);
        return mapToGraphUnitMonthDto(graph);
    }

    public List<GraphYearDto> getGraphByPropertyYear(Integer propertyId) {
        GraphProperty graph = graphRepository.findByPropertyId(propertyId);
        return mapToGraphPropertyYearDto(graph);
    }

    public List<GraphYearDto> getGraphByUnitYear(Integer propertyId) {
        GraphUnit graph = graphUnitRepository.findByPropertyId(propertyId);
        return mapToGraphUnitYearDto(graph);
    }

    public GraphProperty addGraphProperty(GraphProperty graph) {
        GraphProperty graphSaved = graphRepository.save(graph);
        return graphSaved;
    }

    public GraphUnit addGraphUnit(GraphUnit graph) {
        GraphUnit graphSaved = graphUnitRepository.save(graph);
        return graphSaved;
    }

    private List<GraphWeekDto> mapToGraphPropertyWeekDto(GraphProperty graph) {
        List<GraphWeekDto> graphWeekDtos = new ArrayList<>();
        GraphWeekDto graphEstWeekDto = new GraphWeekDto();
        graphEstWeekDto.setPropertyId(graph.getPropertyId());
        graphEstWeekDto.setId(1);
        graphEstWeekDto.setSun(graph.getEstWeek().getSun());
        graphEstWeekDto.setMon(graph.getEstWeek().getMon());
        graphEstWeekDto.setTue(graph.getEstWeek().getTue());
        graphEstWeekDto.setWed(graph.getEstWeek().getWed());
        graphEstWeekDto.setThu(graph.getEstWeek().getThu());
        graphEstWeekDto.setFri(graph.getEstWeek().getFri());
        graphEstWeekDto.setSat(graph.getEstWeek().getSat());
        GraphWeekDto graphCurWeekDto = new GraphWeekDto();
        graphCurWeekDto.setPropertyId(graph.getPropertyId());
        graphCurWeekDto.setId(1);
        graphCurWeekDto.setSun(graph.getEstWeek().getSun());
        graphCurWeekDto.setMon(graph.getEstWeek().getMon());
        graphCurWeekDto.setTue(graph.getEstWeek().getTue());
        graphCurWeekDto.setWed(graph.getEstWeek().getWed());
        graphCurWeekDto.setThu(graph.getEstWeek().getThu());
        graphCurWeekDto.setFri(graph.getEstWeek().getFri());
        graphCurWeekDto.setSat(graph.getEstWeek().getSat());
        graphWeekDtos.add(graphEstWeekDto);
        graphWeekDtos.add(graphCurWeekDto);
        return graphWeekDtos;
    }

    private List<GraphWeekDto> mapToGraphUnitWeekDto(GraphUnit graph) {
        List<GraphWeekDto> graphWeekDtos = new ArrayList<>();
        GraphWeekDto graphEstWeekDto = new GraphWeekDto();
        graphEstWeekDto.setPropertyId(graph.getPropertyId());
        graphEstWeekDto.setId(1);
        graphEstWeekDto.setSun(graph.getEstWeek().getSun());
        graphEstWeekDto.setMon(graph.getEstWeek().getMon());
        graphEstWeekDto.setTue(graph.getEstWeek().getTue());
        graphEstWeekDto.setWed(graph.getEstWeek().getWed());
        graphEstWeekDto.setThu(graph.getEstWeek().getThu());
        graphEstWeekDto.setFri(graph.getEstWeek().getFri());
        graphEstWeekDto.setSat(graph.getEstWeek().getSat());
        GraphWeekDto graphCurWeekDto = new GraphWeekDto();
        graphCurWeekDto.setPropertyId(graph.getPropertyId());
        graphCurWeekDto.setId(1);
        graphCurWeekDto.setSun(graph.getEstWeek().getSun());
        graphCurWeekDto.setMon(graph.getEstWeek().getMon());
        graphCurWeekDto.setTue(graph.getEstWeek().getTue());
        graphCurWeekDto.setWed(graph.getEstWeek().getWed());
        graphCurWeekDto.setThu(graph.getEstWeek().getThu());
        graphCurWeekDto.setFri(graph.getEstWeek().getFri());
        graphCurWeekDto.setSat(graph.getEstWeek().getSat());
        graphWeekDtos.add(graphEstWeekDto);
        graphWeekDtos.add(graphCurWeekDto);
        return graphWeekDtos;
    }

    private List<GraphMonthDto> mapToGraphPropertyMonthDto(GraphProperty graph) {
        List<GraphMonthDto> graphMonthDtos = new ArrayList<>();
        GraphMonthDto estMonthDto = new GraphMonthDto();
        estMonthDto.setPropertyId(graph.getPropertyId());
        estMonthDto.setId(graph.getId());
        estMonthDto.setWeek1(graph.getEstMonth().getWeek1());
        estMonthDto.setWeek2(graph.getEstMonth().getWeek2());
        estMonthDto.setWeek3(graph.getEstMonth().getWeek3());
        estMonthDto.setWeek4(graph.getEstMonth().getWeek4());
        GraphMonthDto curMonthDto = new GraphMonthDto();
        curMonthDto.setPropertyId(graph.getPropertyId());
        curMonthDto.setId(graph.getId());
        curMonthDto.setWeek1(graph.getEstMonth().getWeek1());
        curMonthDto.setWeek2(graph.getEstMonth().getWeek2());
        curMonthDto.setWeek3(graph.getEstMonth().getWeek3());
        curMonthDto.setWeek4(graph.getEstMonth().getWeek4());
        graphMonthDtos.add(estMonthDto);
        graphMonthDtos.add(curMonthDto);
        return graphMonthDtos;
    }

    private List<GraphMonthDto> mapToGraphUnitMonthDto(GraphUnit graph) {
        List<GraphMonthDto> graphMonthDtos = new ArrayList<>();
        GraphMonthDto estMonthDto = new GraphMonthDto();
        estMonthDto.setPropertyId(graph.getPropertyId());
        estMonthDto.setId(graph.getId());
        estMonthDto.setWeek1(graph.getEstMonth().getWeek1());
        estMonthDto.setWeek2(graph.getEstMonth().getWeek2());
        estMonthDto.setWeek3(graph.getEstMonth().getWeek3());
        estMonthDto.setWeek4(graph.getEstMonth().getWeek4());
        GraphMonthDto curMonthDto = new GraphMonthDto();
        curMonthDto.setPropertyId(graph.getPropertyId());
        curMonthDto.setId(graph.getId());
        curMonthDto.setWeek1(graph.getEstMonth().getWeek1());
        curMonthDto.setWeek2(graph.getEstMonth().getWeek2());
        curMonthDto.setWeek3(graph.getEstMonth().getWeek3());
        curMonthDto.setWeek4(graph.getEstMonth().getWeek4());
        graphMonthDtos.add(estMonthDto);
        graphMonthDtos.add(curMonthDto);
        return graphMonthDtos;
    }

    private List<GraphYearDto> mapToGraphPropertyYearDto(GraphProperty graph) {
        List<GraphYearDto> graphYearDtos = new ArrayList<>();
        GraphYearDto estYearDto = new GraphYearDto();
        estYearDto.setPropertyId(graph.getPropertyId());
        estYearDto.setId(graph.getId());
        estYearDto.setJan(graph.getEstYear().getJan());
        estYearDto.setFeb(graph.getEstYear().getFeb());
        estYearDto.setMar(graph.getEstYear().getMar());
        estYearDto.setApr(graph.getEstYear().getApr());
        estYearDto.setMay(graph.getEstYear().getMay());
        estYearDto.setJun(graph.getEstYear().getJun());
        estYearDto.setJul(graph.getEstYear().getJul());
        estYearDto.setAug(graph.getEstYear().getAug());
        estYearDto.setSep(graph.getEstYear().getSep());
        estYearDto.setOct(graph.getEstYear().getOct());
        estYearDto.setNov(graph.getEstYear().getNov());
        estYearDto.setDec(graph.getEstYear().getDec());
        GraphYearDto curYearDto = new GraphYearDto();
        curYearDto.setPropertyId(graph.getPropertyId());
        curYearDto.setId(graph.getId());
        curYearDto.setJan(graph.getCurYear().getJan());
        curYearDto.setFeb(graph.getCurYear().getFeb());
        curYearDto.setMar(graph.getCurYear().getMar());
        curYearDto.setApr(graph.getCurYear().getApr());
        curYearDto.setMay(graph.getCurYear().getMay());
        curYearDto.setJun(graph.getCurYear().getJun());
        curYearDto.setJul(graph.getCurYear().getJul());
        curYearDto.setAug(graph.getCurYear().getAug());
        curYearDto.setSep(graph.getCurYear().getSep());
        curYearDto.setOct(graph.getCurYear().getOct());
        curYearDto.setNov(graph.getCurYear().getNov());
        curYearDto.setDec(graph.getCurYear().getDec());
        graphYearDtos.add(estYearDto);
        graphYearDtos.add(curYearDto);
        return graphYearDtos;
    }

    private List<GraphYearDto> mapToGraphUnitYearDto(GraphUnit graph) {
        List<GraphYearDto> graphYearDtos = new ArrayList<>();
        GraphYearDto estYearDto = new GraphYearDto();
        estYearDto.setPropertyId(graph.getPropertyId());
        estYearDto.setId(graph.getId());
        estYearDto.setJan(graph.getEstYear().getJan());
        estYearDto.setFeb(graph.getEstYear().getFeb());
        estYearDto.setMar(graph.getEstYear().getMar());
        estYearDto.setApr(graph.getEstYear().getApr());
        estYearDto.setMay(graph.getEstYear().getMay());
        estYearDto.setJun(graph.getEstYear().getJun());
        estYearDto.setJul(graph.getEstYear().getJul());
        estYearDto.setAug(graph.getEstYear().getAug());
        estYearDto.setSep(graph.getEstYear().getSep());
        estYearDto.setOct(graph.getEstYear().getOct());
        estYearDto.setNov(graph.getEstYear().getNov());
        estYearDto.setDec(graph.getEstYear().getDec());
        GraphYearDto curYearDto = new GraphYearDto();
        curYearDto.setPropertyId(graph.getPropertyId());
        curYearDto.setId(graph.getId());
        curYearDto.setJan(graph.getCurYear().getJan());
        curYearDto.setFeb(graph.getCurYear().getFeb());
        curYearDto.setMar(graph.getCurYear().getMar());
        curYearDto.setApr(graph.getCurYear().getApr());
        curYearDto.setMay(graph.getCurYear().getMay());
        curYearDto.setJun(graph.getCurYear().getJun());
        curYearDto.setJul(graph.getCurYear().getJul());
        curYearDto.setAug(graph.getCurYear().getAug());
        curYearDto.setSep(graph.getCurYear().getSep());
        curYearDto.setOct(graph.getCurYear().getOct());
        curYearDto.setNov(graph.getCurYear().getNov());
        curYearDto.setDec(graph.getCurYear().getDec());
        graphYearDtos.add(estYearDto);
        graphYearDtos.add(curYearDto);
        return graphYearDtos;
    }


}
