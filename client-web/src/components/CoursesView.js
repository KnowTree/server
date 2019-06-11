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
                <button >Edit</button>
                <button onClick={this.deleteItem.bind(this, item.id)}>Delete</button>
            </div>
        )
    }


    toolbarItems() {
        return (
            <div>
                <button className="btn btn-primary">Add</button>
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