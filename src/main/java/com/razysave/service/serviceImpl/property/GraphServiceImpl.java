package com.razysave.service.serviceImpl.property;

import com.razysave.dto.property.GraphMonthDto;
import com.razysave.dto.property.GraphWeekDto;
import com.razysave.dto.property.GraphYearDto;
import com.razysave.entity.property.GraphProperty;
import com.razysave.entity.property.GraphUnit;
import com.razysave.exception.GraphNotFoundException;
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
        if (graph != null)
        return mapToGraphPropertyWeekDto(graph);
        else
            throw new GraphNotFoundException("Graph not found");
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
        graphEstWeekDto.setWeek(graph.getEstWeek());
        GraphWeekDto graphCurWeekDto = new GraphWeekDto();
        graphCurWeekDto.setPropertyId(graph.getPropertyId());
        graphCurWeekDto.setId(1);
        graphCurWeekDto.setWeek(graph.getCurWeek());
        graphWeekDtos.add(graphEstWeekDto);
        graphWeekDtos.add(graphCurWeekDto);
        return graphWeekDtos;
    }

    private List<GraphWeekDto> mapToGraphUnitWeekDto(GraphUnit graph) {
        List<GraphWeekDto> graphWeekDtos = new ArrayList<>();
        GraphWeekDto graphEstWeekDto = new GraphWeekDto();
        graphEstWeekDto.setPropertyId(graph.getPropertyId());
        graphEstWeekDto.setId(1);
        graphEstWeekDto.setWeek(graph.getEstWeek());
        GraphWeekDto graphCurWeekDto = new GraphWeekDto();
        graphCurWeekDto.setPropertyId(graph.getPropertyId());
        graphCurWeekDto.setId(1);
        graphCurWeekDto.setWeek(graph.getCurWeek());
        graphWeekDtos.add(graphEstWeekDto);
        graphWeekDtos.add(graphCurWeekDto);
        return graphWeekDtos;
    }

    private List<GraphMonthDto> mapToGraphPropertyMonthDto(GraphProperty graph) {
        List<GraphMonthDto> graphMonthDtos = new ArrayList<>();
        GraphMonthDto estMonthDto = new GraphMonthDto();
        estMonthDto.setPropertyId(graph.getPropertyId());
        estMonthDto.setId(graph.getId());
        estMonthDto.setMonth(graph.getEstMonth());
        GraphMonthDto curMonthDto = new GraphMonthDto();
        curMonthDto.setPropertyId(graph.getPropertyId());
        curMonthDto.setId(graph.getId());
        curMonthDto.setMonth(graph.getCurMonth());
        graphMonthDtos.add(estMonthDto);
        graphMonthDtos.add(curMonthDto);
        return graphMonthDtos;
    }

    private List<GraphMonthDto> mapToGraphUnitMonthDto(GraphUnit graph) {
        List<GraphMonthDto> graphMonthDtos = new ArrayList<>();
        GraphMonthDto estMonthDto = new GraphMonthDto();
        estMonthDto.setPropertyId(graph.getPropertyId());
        estMonthDto.setId(graph.getId());
        estMonthDto.setMonth(graph.getEstMonth());
        GraphMonthDto curMonthDto = new GraphMonthDto();
        curMonthDto.setPropertyId(graph.getPropertyId());
        curMonthDto.setId(graph.getId());
        estMonthDto.setMonth(graph.getEstMonth());
        graphMonthDtos.add(estMonthDto);
        graphMonthDtos.add(curMonthDto);
        return graphMonthDtos;
    }

    private List<GraphYearDto> mapToGraphPropertyYearDto(GraphProperty graph) {
        List<GraphYearDto> graphYearDtos = new ArrayList<>();
        GraphYearDto estYearDto = new GraphYearDto();
        estYearDto.setPropertyId(graph.getPropertyId());
        estYearDto.setId(graph.getId());
        estYearDto.setYear(graph.getEstYear());
        GraphYearDto curYearDto = new GraphYearDto();
        curYearDto.setPropertyId(graph.getPropertyId());
        curYearDto.setId(graph.getId());
        curYearDto.setYear(graph.getCurYear());
        graphYearDtos.add(estYearDto);
        graphYearDtos.add(curYearDto);
        return graphYearDtos;
    }

    private List<GraphYearDto> mapToGraphUnitYearDto(GraphUnit graph) {
        List<GraphYearDto> graphYearDtos = new ArrayList<>();
        GraphYearDto estYearDto = new GraphYearDto();
        estYearDto.setPropertyId(graph.getPropertyId());
        estYearDto.setId(graph.getId());
        estYearDto.setYear(graph.getEstYear());
        GraphYearDto curYearDto = new GraphYearDto();
        curYearDto.setPropertyId(graph.getPropertyId());
        curYearDto.setId(graph.getId());
        curYearDto.setYear(graph.getCurYear());
        graphYearDtos.add(estYearDto);
        graphYearDtos.add(curYearDto);
        return graphYearDtos;
    }


}
