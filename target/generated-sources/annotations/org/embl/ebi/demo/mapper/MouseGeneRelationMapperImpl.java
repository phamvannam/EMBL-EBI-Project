package org.embl.ebi.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.embl.ebi.demo.model.dto.MouseGeneRelationResponse;
import org.embl.ebi.demo.model.internal.HumanGeneRelationDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-19T22:18:51+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class MouseGeneRelationMapperImpl implements MouseGeneRelationMapper {

    @Override
    public MouseGeneRelationResponse toMouseGeneRelationResponse(MouseGeneRelationDetail detail) {
        if ( detail == null ) {
            return null;
        }

        MouseGeneRelationResponse mouseGeneRelationResponse = new MouseGeneRelationResponse();

        mouseGeneRelationResponse.setId( detail.getId() );
        mouseGeneRelationResponse.setName( detail.getName() );
        mouseGeneRelationResponse.setSymbol( detail.getSymbol() );
        mouseGeneRelationResponse.setMgiGeneAccId( detail.getMgiGeneAccId() );
        List<HumanGeneRelationDetail> list = detail.getHumanGeneRelationDetails();
        if ( list != null ) {
            mouseGeneRelationResponse.setHumanGeneRelationDetails( new ArrayList<HumanGeneRelationDetail>( list ) );
        }

        return mouseGeneRelationResponse;
    }
}
