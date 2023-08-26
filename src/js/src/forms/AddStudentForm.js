import { Formik } from "formik";
import { Input, Button, Radio } from "antd";

const inputTopMargin = {marginTop: '30px'};

const AddStudentForm = () => (
     <Formik
       initialValues={{ firstName: '', lastName: '', email: '', gender: '' }}
       validate={values => {
         const errors = {};
                  
         if (!values.firstName){
            errors.firstName = 'First Name Required';
         }

         if (!values.lastName){
            errors.lastName = 'Last Name Required';
         }

         if (!values.email) {
           errors.email = 'Email Required';
         } else if (
           !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
         ) {
           errors.email = 'Invalid email address';
         }

         if (!values.gender){
            errors.gender = 'Gender Requiered'
         }

         return errors;
       }}
       onSubmit={(values, { setSubmitting }) => {
         setTimeout(() => {
           alert(JSON.stringify(values, null, 2));
           setSubmitting(false);
         }, 400);
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
                name="firstName"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.firstName}
                placeholder="FirstName. E.g. John"
           />
           {errors.firstName && touched.firstName && 
                <span className="text-danger">{errors.firstName}</span>}
            <Input
                style={inputTopMargin}
                name="lastName"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.lastName}
                placeholder="Last Name. E.g. Smith"
           />
           {errors.lastName && touched.lastName && 
                <span className="text-danger">{errors.lastName}</span>}
           <Input
                style={inputTopMargin}
                type="email"
                name="email"
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.email}
                placeholder="EmaiL. E.g. johnsmith@mail.com"
           />
            {errors.email && touched.email && 
                <span className="text-danger">{errors.email}</span>}
            <div style={inputTopMargin}>
            <Radio.Group >
                <Radio 
                        name="gender"
                        value="MALE">MALE</Radio>
                <Radio
                        name="gender"
                        value="FEMALE">FEMALE</Radio>
            </Radio.Group>
            {errors.email && touched.email && 
                <div className="text-danger">{errors.gender}</div>}
            </div>
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

 export default AddStudentForm;