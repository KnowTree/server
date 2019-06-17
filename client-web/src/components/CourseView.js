import {React} from 'CakeReact';
import {Link} from 'react-router-dom';
import KNodeList from './KNodeList';
import CourseBasicInfoForm from "./CourseBasicInfoForm";

class CourseView extends React.Component{
    constructor() {
        super();
    }

    render() {
        const course_id = this.props ? (this.props.params.course_id || this.props.course_id) : null;
        return (
            <div>
                <h5>Course {course_id}</h5>
                <div className="row">
                    <div className="col-md-4">
                        <CourseBasicInfoForm course_id={course_id} isNew={false}/>
                    </div>
                    <div className="col-md-8">
                        <KNodeList course_id={course_id}/>
                    </div>
                </div>
            </div>
        )

    }
}

export default CourseView;