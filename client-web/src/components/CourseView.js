import {React} from 'CakeReact';
import {Link} from 'react-router-dom';
import KNodeList from './KNodeList';
import CourseBasicInfoForm from "./CourseBasicInfoForm";

class CourseView extends React.Component{
    constructor() {
        super();
    }

    render() {
        const course_id = this.props ? (this.props.match.params.course_id || this.props.course_id) : null;
        return (
            <div>
                <h4>Course {course_id}</h4>
                <CourseBasicInfoForm course_id={course_id} isNew={false}/>
                <KNodeList course_id={course_id}/>
            </div>
        )

    }
}

export default CourseView;