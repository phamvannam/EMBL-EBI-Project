package org.embl.ebi.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.embl.ebi.demo.model.dto.MouseGeneResponse;
import org.embl.ebi.demo.model.dto.MouseGeneSymbolSynonymResponse;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-19T22:18:51+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class MouseGeneMapperImpl implements MouseGeneMapper {

    @Override
    public MouseGeneDetail toMouseGeneDetail(MouseGeneEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MouseGeneDetail mouseGeneDetail = new MouseGeneDetail();

        mouseGeneDetail.setId( entity.getId() );
        mouseGeneDetail.setName( entity.getName() );
        mouseGeneDetail.setSymbol( entity.getSymbol() );
        mouseGeneDetail.setMgiGeneAccId( entity.getMgiGeneAccId() );

        return mouseGeneDetail;
    }

    @Override
    public MouseGeneResponse toMouseGeneResponse(MouseGeneDetail detail) {
        if ( detail == null ) {
            return null;
        }

        MouseGeneResponse mouseGeneResponse = new MouseGeneResponse();

        mouseGeneResponse.setId( detail.getId() );
        mouseGeneResponse.setName( detail.getName() );
        mouseGeneResponse.setSymbol( detail.getSymbol() );
        mouseGeneResponse.setMgiGeneAccId( detail.getMgiGeneAccId() );

        return mouseGeneResponse;
    }

    @Override
    public MouseGeneSymbolSynonymResponse toMouseGeneSymbolSynonymResponse(MouseGeneDetail detail) {
        if ( detail == null ) {
            return null;
        }

        MouseGeneSymbolSynonymResponse mouseGeneSymbolSynonymResponse = new MouseGeneSymbolSynonymResponse();

        mouseGeneSymbolSynonymResponse.setId( detail.getId() );
        mouseGeneSymbolSynonymResponse.setName( detail.getName() );
        mouseGeneSymbolSynonymResponse.setSymbol( detail.getSymbol() );
        mouseGeneSymbolSynonymResponse.setMgiGeneAccId( detail.getMgiGeneAccId() );
        List<String> list = detail.getSynonyms();
        if ( list != null ) {
            mouseGeneSymbolSynonymResponse.setSynonyms( new ArrayList<String>( list ) );
        }

        return mouseGeneSymbolSynonymResponse;
    }
}
