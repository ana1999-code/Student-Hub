import { Formik } from "formik";
import { Input, Button } from "antd";
import { addNewCourse } from "../Client";

const inputTopMargin = {marginTop: '30px'};

const AddCourseForm = (props) => (
     <Formik
       initialValues={{ name: '', description: '', department: '', teacherName: '' }}
       validate={values => {
         const errors = {};
                  
         if (!values.name){
            errors.name = 'Course Name Required';
         }

         if (!values.description){
            errors.description = 'Description Required';
         }

         if (!values.department) {
           errors.email = 'Department Required';
         } 

         return errors;
       }}
       onSubmit={(course, { setSubmitting }) => {
            addNewCourse(course).then(() => {
            props.onSuccess();
       }).catch(error => {
            props.onFailure(error);
       })
       .finally(() => {
            setSubmitting(false);
       })
       }}
     >
       {({
         values,
         errors,
         touched,
         handleChange,
         handleBlur,
         handleSubmit,
         isSubmitting,
         submitForm,
         isValid
       }) => (
         <form onSubmit={handleSubmit}>
            <Input
                style={inputTopMargin}
                name="name"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.name}
                placeholder="Course Name. E.g. Java"
           />
           {errors.name && touched.name && 
                <span className="text-danger">{errors.name}</span>}
            <Input
                style={inputTopMargin}
                name="description"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.description}
                placeholder="Descritpion. E.g. Programming Language"
           />
           {errors.description && touched.description && 
                <span className="text-danger">{errors.description}</span>}
           <Input
                style={inputTopMargin}
                name="department"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.department}
                placeholder="Department. E.g. Computer Science"
           />
            {errors.department && touched.department && 
                <span className="text-danger">{errors.department}</span>}
            <Input
                style={inputTopMargin}
                name="teacherName"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.teacherName}
                placeholder="Teacher Name. E.g. John Smith"
           />
           <Button 
                style={inputTopMargin} 
                onClick={() => submitForm()} 
                disabled={isSubmitting | (touched && !isValid)}>
             Submit
           </Button>
         </form>
       )}
     </Formik>
 );

 export default AddCourseForm;