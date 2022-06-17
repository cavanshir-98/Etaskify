package webApplication.Etaskify.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.resource.organization.OrganizationSearchRequest;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    Optional<Organization> findByName(String name);

    default Page<Organization> findAll(OrganizationSearchRequest searchReq) {
        return findAll((root, criteria, cb) -> {
            Predicate predicate = cb.conjunction();

            if (searchReq.getId() != null) {
                predicate = cb.and(predicate, root.get(Organization.Fields.id).in(searchReq.getId()));
            }
            if (searchReq.getName() != null) {
                predicate = cb.and(predicate, root.get(Organization.Fields.name).in(searchReq.getName()));
            }

            return predicate;
        }, PageRequest.of(searchReq.getPage(), searchReq.getSize(), Sort.by(Sort.Direction.valueOf(searchReq.getDir()), searchReq.getOrder())));
    }
}
