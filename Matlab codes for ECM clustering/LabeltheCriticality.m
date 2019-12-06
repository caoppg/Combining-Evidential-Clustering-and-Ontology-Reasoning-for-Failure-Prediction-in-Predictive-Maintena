%generate random cost, uniformly distributed from [0,100] 
Cost = 0 + (100-0).*rand(size(Time,1),1);
Total=[Time,Cost,Support]
%credal partition for Time and Cost. A 3-credal partition is imposed so that 2^3=8 focal elements have been considered in the optimization process
Costbba=ECM(Cost, 3)
Timebba=ECM(Time, 3)
%multiply cost and time, save at column 4
for i=1:size(Total,1)
    Total(i,4)=-Total(i,1)*Total(i,2)
end
%ECM
for i= 1:size(Support,1)
    X(:,1)=Costbba(i,:)'
    Y(:,1)=Timebba(i,:)'
    A(i,:)=discounting(X(:,1),Support(i,1))
    B(i,:)=discounting(Y(:,1),Support(i,1))
end
%conjunctive rule of combination, two bbas
for j=1:size(A,1)
q1=mtoq(A(j,:));
q2=mtoq(B(j,:));
mConj1=qtom(q1);
mConj2=qtom(q2);
Betp1=mtobetp(mConj1); % sum to 1, COST
Betp2=mtobetp(mConj2); % sum to 1, TIME
Result1(j,:)=Betp1;
Result2(j,:)=Betp2;
end
%the  final  level  of criticality, apply argmax. The first column of
%matrix "Decision" is the max pignistic probability , and the second column is the
%cluster to which the max pignistic probability belongs.
for k=1:size(A,1)
    [argvalue, argmax] = max(Result1(k,:))
    
    %max pignistic probability 
    DecisionTime(k,1)=argvalue
    % which cluster the max  pignistic probability belongs to
    DecisionTime(k,2)=argmax
end
for k=1:size(A,1)
    [argvalue, argmax] = max(Result2(k,:))
    
    %max pignistic probability 
    DecisionCost(k,1)=argvalue
    % which cluster the max  pignistic probability belongs to
    DecisionCost(k,2)=argmax
end
%find boundaries for Cost
%find lm bound for cost
indexlm=find(DecisionCost(:,2)==1);
CostVector1=Total(:,2);
costlmbound=min(CostVector1(indexlm));
%find mh bound for cost
indexmh=find(DecisionCost(:,2)==2);
CostVector2=Total(:,2);
costmhbound=max(CostVector2(indexmh));

%find lm bound for time
indextlm=find(DecisionTime(:,2)==1);
TimeVector1=Total(:,2);
timelmbound=-min(TimeVector1(indextlm));
%find mh bound for time
indextmh=find(DecisionTime(:,2)==2);
TimeVector2=Total(:,2);
timemhbound=-max(TimeVector2(indextlm));

%merge the boundaries
finallmbound=costlmbound*timelmbound;
finalmhbound=costmhbound*timemhbound;

%label the data with final criticality
for m=1:size(Total,1)
    if Total(m,4)<finallmbound
        Total(m,5)=1;
    elseif finallmbound<= Total(m,4)<finalmhbound
        Total(m,5)=2;
    elseif Total(m,4)>=finalmhbound
        Total(m,5)=3;
    end
end


