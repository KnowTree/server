import {React, List, AlertManager, BrowserUtils} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";

class CoursesView extends List {
    constructor(props) {
        super(props);
        this.goToNewCourse = this.goToNewCourse.bind(this);
        this.deleteItem = this.deleteItem.bind(this);
        this.goToEditCourse = this.goToEditCourse.bind(this);
    }

    doFetch() {
        const option = {
            l : this.size(),
            s : this.startIndex(),
            cursor : this.cursor()
        };
        search("Course", {}, option,
                result => this.setItems(result.data),
                error => AlertManager.show('error', error.message));
    }

    renderHeader() {
        return (
            <h5>Courses</h5>
        )
    }

    renderRow(item, index) {
        return (
            <div className="card col-md-3 ml-1 mr-1 mt-1 mb-1">
                <div className="card-body">
                    <h5>{item.title}</h5>
                    <p>#{item.id}</p>
                </div>
                <div className="card-body">
                    <a className="card-link" onClick={this.goToEditCourse} data={item.id}>Edit</a>
                    <a className="card-link" onClick={this.deleteItem} data={item.id}>Delete</a>
                </div>
            </div>
        )
    }


    toolbarItems() {
        return (
            <div>
                <button onClick={this.goToNewCourse} className="btn btn-secondary btn-sm">Add</button>
            </div>
        )
    }

    doSearch(value) {

    }

    deleteItem(e) {
        const id = e.target.getAttribute("data");
        deleteEntity("Course", id,
            (result) => this.doFetch(),
                error => AlertManager.show('error', error.message));
    }

    goToNewCourse() {
        BrowserUtils.changeUrlAndEmitEvent("/new_course");
    }

    goToEditCourse(e) {
        const id = e.target.getAttribute("data");
        const url = "/course/" + id;
        BrowserUtils.changeUrlAndEmitEvent(url);
    }
}

export default CoursesView;