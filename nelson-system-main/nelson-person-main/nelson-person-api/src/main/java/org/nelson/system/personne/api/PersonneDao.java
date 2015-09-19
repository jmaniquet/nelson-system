package org.nelson.system.personne.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.nelson.system.core.api.mybatis.mapper.CoreMapper;
import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneDao extends CoreMapper {

	List<Personne> findByCriteria(@Param("criteria") PersonneRechercheCriteria criteria);
}
