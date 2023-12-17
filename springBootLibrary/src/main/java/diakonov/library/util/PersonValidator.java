package diakonov.library.util;

import diakonov.library.dao.PersonDAO;
import diakonov.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
Person person=(Person) target;
if(personDAO.isPersonInDataBase(person.getPersonName()).isPresent()){
errors.rejectValue("personName", "Такой читатель уже существует в базе данных");
}
    }
}
