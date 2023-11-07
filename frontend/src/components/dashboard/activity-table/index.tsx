'use client'

import { ActivitiesType, ActivityType } from "@/app/api/activities/route";
import { ActivityTableContext } from "@/context/activity-table-context";
import { frontendApi } from "@/lib/api/axios";
import { useContext } from "react";
import { columns } from "./columns";
import { DataTable } from "./data-table";

async function getData(): Promise<ActivityType[]> {

  var data: ActivityType[] = [];

  try {
    const result = await frontendApi.get("/activities");

    const { activities } = result.data as ActivitiesType;

    if (activities) {
      data = activities;
    }

  } catch (e) {
    data = [];
  }

  return data;
}

export default function ActivityTable() {

  const activityTableContext = useContext(ActivityTableContext);

  const activities = activityTableContext.activities;

  return (
    <div className="container mx-auto my-8">
      <DataTable columns={columns} data={activities} />
    </div>
  )
}