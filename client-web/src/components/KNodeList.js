import React from "react";
import List from "../widgets/table/List";
import {search, deleteEntity} from "../utils/ApiCall";

class KNodeList extends List {
   doFetch() {
       const option = {
           l : this.size(),
           s : this.startIndex(),
           cursor : this.cursor()
       };
       const course_id = this.props.course_id;
       if (course_id) {
        search("Knode", {course_id : this.props.course_id}, option, (result) => this.setItems(result.data), error => this.setError(error));
       } else {
           this.setItems([]);
       }
   }

   renderHeader() {
       return (
           <th>
               <td>ID</td>
               <td>Title</td>
           </th>
       )
   }

   renderRow(item, index) {
       let editUrl = "/node/"+item.id;
       return (
           <tr>
               <td>{item.id}</td>
               <td>{item.title}</td>
               <td><a href={editUrl}> Edit</a><button onClick={this.deleteItem}>Delete</button></td>

           </tr>
       )
   }

   renderPager() {

   }

   toolbar() {
       const addUrl = "/courses/" + this.props.course_id + "/new_node";
       return (
           <div>
               <a href={addUrl}>Add</a>
           </div>
       )
   }

   deleteItem(e) {
       const index = e.target.data;
       const item = this.state.items[index];
       deleteEntity("node", item.id, (result) => {
           this.doFetch();
       }, error => this.setState({error : error}))
   }
}

export default KNodeList;