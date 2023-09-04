import { useState, useEffect } from 'react';
import { getAllCourses, deleteCourse } from './Client.js';
import { LoadingOutlined } from '@ant-design/icons';
import { Table, Spin, Empty, Modal, Button } from 'antd';
import Container from './Container.js';
import Footer from './Footer.js';
import { errorNotification, successNotification } from './Notification.js';
import AddCourseForm from './forms/AddCourseForm';
import { getIndicatorIcon } from './Spin.js';

function Courses() {
  const [courses, setCourses] = useState([]);
  const [isFetching, setFetching] = useState(true);
  const [isAddCourseModalVisisble, setAddCourseModalVisible] = useState(false);

  useEffect(() => {
    fetchAllCourses();
  }, []);

  const fetchAllCourses = () => {
    getAllCourses()
    .then(res => res.json()
    .then(courses => {
      console.log(courses);
      setCourses(courses);
      setFetching(false);
    }))
    .catch(error => {
      console.log(error);
      errorNotification(error.error.message);
      setFetching(false);
    });
  }

  const openAddCourseModal = () => {
    setAddCourseModalVisible(true);
  };
  
  const closeAddCourseModal = () => {
    setAddCourseModalVisible(false);
  };

  const deleteCourseById = (courseId) => {
    deleteCourse(courseId)
    .then(() => fetchAllCourses())
    .catch(error => {
      errorNotification(error.error.message);
    });
  }

  const commonElements = () => (
    <div>
      <Modal 
            title="Add New Course" 
            open={isAddCourseModalVisisble} 
            onOk={closeAddCourseModal} 
            onCancel={closeAddCourseModal}
            > 
            <AddCourseForm 
              onSuccess={()=> {
                closeAddCourseModal();
                fetchAllCourses();
                successNotification("Course added successfully!");
                }}
              onFailure={(error) => {
                errorNotification(error.error.message);
              }}/>
          </Modal>
        <Footer 
          numberOfElements={courses.length} 
          handleClickEvent={openAddCourseModal}
          addElementButtonName="Add New Course" 
         />
    </div>
  )

  const columns = [
    {
      title: 'Course',
      dataIndex: 'name',
      key: 'name'
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description'
    },
    {
      title: 'Department',
      dataIndex: 'department',
      key: 'department'
    },
    {
      title: 'Teacher',
      dataIndex: 'teacherName',
      key: 'teacherName'
    },
    {
      title: '',
      key: 'deleteCourse',
      render: (course) => (
        <Button type='primary' danger onClick={() => deleteCourseById(course.courseId)}>Delete</Button>
      )
    }
  ]

  if(isFetching){
    return (
      <Container>
        <Spin indicator={getIndicatorIcon()} />
      </Container>
    )
  }

  if(courses && courses.length){
    return (
      <Container>
        <Table 
          dataSource={courses} 
          columns={columns}
          rowKey='courseId'
          pagination={{pageSize: 5}}/>
        {commonElements()}
      </Container>
    )
  }
   
  
  return (
    <Container>
      <Empty description={
        <h1>No Courses Found!</h1>
      }/>
      {commonElements()}
    </Container>
  );
}

export default Courses;