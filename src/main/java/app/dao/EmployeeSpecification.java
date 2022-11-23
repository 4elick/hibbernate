package app.dao;

import app.dto.FilterDTO;
import app.entity.Employee;
import app.entity.Status;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification implements Specification<Employee> {
    private FilterDTO filterDTO;
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(filterDTO.getBirthdateLessThan() != null && !"".equals(filterDTO.getBirthdateLessThan())){
            predicates.add(criteriaBuilder.lessThan(root.get("birthdate"),filterDTO.getBirthdateLessThan()));
        }
        if(filterDTO.getBirthdateMoreThan() != null && !"".equals(filterDTO.getBirthdateMoreThan())){
            predicates.add(criteriaBuilder.greaterThan(root.get("birthdate"), filterDTO.getBirthdateMoreThan()));
        }
        if(filterDTO.getStatus() != null && !filterDTO.getStatus().isEmpty()){
            CriteriaBuilder.In<Status> in = criteriaBuilder.in(root.get("status"));
            for(Status s : filterDTO.getStatus()){
                in.value(s);
            };
            predicates.add(in);
        }
        if(filterDTO.getPersonalNumber() != null && !"".equals(filterDTO.getPersonalNumber())){

            predicates.add(criteriaBuilder.like(root.get("personalNumber"),"%"+ filterDTO.getPersonalNumber()+"%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
    public EmployeeSpecification(FilterDTO filter){
        this.filterDTO = filter;
    }
}
