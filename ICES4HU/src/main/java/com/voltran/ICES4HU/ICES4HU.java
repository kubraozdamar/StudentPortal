package com.voltran.ICES4HU;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ICES4HU {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ICES4HU.class, args);
    }

    // Sample code to test database connectionss

 /*   public void testDatabaseConnection() {
        String sql = "SELECT COUNT(*) FROM my_table";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Number of rows in my_table: " + rowCount);
    }
*/
/*
    @EventListener
    public void logDatabaseConnectionStatus(ContextRefreshedEvent event) {

        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database connection status: Connected");
        } catch (Exception e) {
            System.out.println("Database connection status: Disconnected");
        }
    }
*/

    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "index.html";
        }
		@GetMapping("/register")
        public String register() {return "html/register.html";
        }
        
/*        @RequestMapping("/main_page")
        public String display() {return "main_page.html";}*/
		
        @GetMapping("/main_page")
        public String display() {return "html/main_page.html";}
        @GetMapping("/main_page_admin")
        public String displayAdmin() {return "html/main_page_admin.html";}
        @GetMapping("/main_page_instructor")
        public String displayInstructor() {return "html/main_page_instructor.html";}

        @GetMapping("/edit_account")
        public String editAccount() {return "html/edit_account.html";}

        @GetMapping("/edit_account_admin")
        public String editAccountAdmin() {return "html/edit_account_admin.html";}

        @GetMapping("/edit_account_instructor")
        public String editAccountInstructor() {return "html/edit_account_instructor.html";}

        @GetMapping("/list_transcript")
        public String listTranscript() {return "html/list_transcript.html";}

        @GetMapping("/approve_user")
        public String approveUser() {return "html/approve_user.html";}

        @GetMapping("/courses")
        public String coureses() {return "html/courses.html";}

        @GetMapping("/student_add_drop")
        public String studentAddDrop() {return "html/student_add_drop.html";}

        @GetMapping("/add_course")
        public String addCourse() {return "html/add_course.html";}

        @GetMapping("/instructor_create_survey")
        public String addCreateSurvey() {return "html/instructor_create_survey.html";}

        @GetMapping("/student_fill_survey")
        public String addFillSurvey() {return "html/student_fill_survey.html";}

        @GetMapping("/instructor_exam")
        public String instructorExam() {return "html/instructor_exam.html";}

        @GetMapping("/admin_register")
        public String adminRegister() {return "html/admin_register.html";}

    }

}