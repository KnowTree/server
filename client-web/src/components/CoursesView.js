import {Link} from "react-router-dom";
import {React, List, AlertManager} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";

class CoursesView extends List {
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
            <h3>Courses</h3>
        )
    }

    renderRow(item, index) {
        const editUrl = "/course/" + item.id;
        return (
            <div>
                <p>ID : {item.id}</p>
                <p>Title : {item.title}</p>
                <Link to={editUrl}>Edit</Link>
                <button onClick={this.deleteItem.bind(this, item.id)}>Delete</button>
            </div>
        )
    }


    toolbarItems() {
        return (
            <div>
                <Link className="btn btn-primary" to="/new_course">Add</Link>
            </div>
        )
    }

    doSearch(value) {

    }

    deleteItem(id) {
        deleteEntity("Course", id,
            (result) => this.doFetch(),
                error => AlertManager.show('error', error.message));
    }
}

export default CoursesView;