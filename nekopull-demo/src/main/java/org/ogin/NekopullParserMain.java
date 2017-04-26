package org.ogin;

import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.cyberneko.pull.XMLEvent;
import org.cyberneko.pull.XMLPullParser;
import org.cyberneko.pull.event.CharactersEvent;
import org.cyberneko.pull.event.ElementEvent;
import org.cyberneko.pull.parsers.Xerces2;
import org.ogin.employee.Employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class NekopullParserMain {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Employee> employeeList = null;
        Employee currEmp = null;
        String tagContent = null;

        XMLPullParser parser = new Xerces2();
        String inputFile = NekopullParserMain.class.getClassLoader().getResource("employees.xml").toURI().getPath();
        XMLInputSource source = new XMLInputSource(null, inputFile, null);
        parser.setInputSource(source);
        // iterate document events
        XMLEvent event;
        while ((event = parser.nextEvent()) != null) {
            if (event.type == XMLEvent.DOCUMENT) {
                employeeList = new ArrayList<Employee>();
            }
            if (event.type == XMLEvent.ELEMENT) {
                ElementEvent elementEvent = (ElementEvent) event;
                switch (elementEvent.element.rawname) {
                    case "employee":
                        XMLAttributes id = elementEvent.attributes;
                        if (id != null) {
                            currEmp = new Employee();
                            currEmp.setId(id.getValue("id"));
                        }
                        break;
                    case "employees":
                        ElementEvent employees = (ElementEvent) event;
                        if (employees.start) {
                            employeeList = new ArrayList<Employee>();
                        }
                        break;
                    case "firstName":
                        XMLEvent ev = elementEvent.next;
//                        CharactersEvent charsEvent = (CharactersEvent) elementEvent.next.next;
//                        charsEvent.
//                        tagContent = charsEvent.text.toString().trim();
//                        currEmp.setFirstName(tagContent);
                        break;
                }
            }
        }

// free resources
        parser.cleanup();
        for (Employee e : employeeList)

        {
            System.out.println(e.getFirstName());
        }

    }
}
